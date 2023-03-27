async function DisplayOrderTasks(){

  const OrderDetails=document.getElementById(`orderTask`)
    const OrderList = []
///////////////////////////////////////////////

    const displayOrders = (orders) => {
      orderDetails.innerHTML = orders.map(
          (o)=> {
            return singleOrder(o)
          }
      ).join('')
    }
    ////////////////////////////////////////////////

    function singleOrder(o) { // ==================================> creating view of single order "c"-> viemodel
      let orderHtml = `<label id="orderDetails-${o.Id}">`
      orderHtml +="|From:${o.addressFrom } To:${o.addressTo}|"
      orderHtml += `<button  class="btn btn-outline-secondary" type="button" id="task - ${o.id}">Take task</button>`
      orderHtml += `</label>`
    const takeTaskButton=document.getElementById(`task - ${o.id}`)
    takeTaskButton.dataset.id=orderTask.id
    takeTaskButton.addEventListener('click', AssignTask())

      return orderHtml
    }
///////////////////////////////////////////////////////

  fetch("http://localhost:8080/order/{id}").
  then(response => response.json()).
  then(orderTask => {


         OrderList.push(orderTask)

                displayOrders(OrderList)
              })


    }

//====================================================================================================================


//==========================================Driver take task=======================================================

//remove Child to take it out from list

//const orderForm = document.getElementById('orderForm')
//orderForm.addEventListener("submit",postFormDataAsJson)



      function AssignTask(event) {
        event.preventDefault();

        //1.remove task from the list(remove child without deleting the order)
        //2.display it sepparatelly
        //3.add order to list of order of the driver --->send order as Json to driver
        //4.inform client the task is taken ---->send order as Json to User
        //5.waiting driver to push "order done" --->button eventlistener

        //driver should not be able to take task if he has such (active status false)

        //================== 1.


     let task = document.getElementById(`"orderDetails-${o.Id}"`);
    if (task.parentNode) {
      task.parentNode.removeChild(task);
    }
    fetch(`http://localhost:8080/orders`).
        then(_ => DisplayOrderTasks()).
        catch(error => console.log('error', error))

        ShowApprovedOrder();
        AssignOrderToDriver();


//==========================2.




fetch("http://localhost:8080/drivers/{id}/currentOrder").
  then(response => response.json()).
  then(order => {

    const CurrentOrder=document.getElementById(`task`)

CurrentOrder.innerHTML=`
    <div>
      <div class="text-center">
          <p class="from">|Adress From: ${order.addressFrom}|</p>
          <p class="to">|Address To: ${order.addressTo}|</p>
      </div>
      <div class="btn-group">
          <a href="..." id="done">Done</a>
      </div>
  </div>`

  })
  document.getElementById("done").addEventListener('click',FinishedOrders) //to check the last position true false(in the serverside if exists the order id active if not not active)
      }
  //==========================3.add order to list of orders of the driver --->send order as Json to driver

  async function AssignOrderToDriver({url, Data}) {

  const url = "http://localhost:8080/drivers/{id}" //find a way to point the principal id or to remove the id from the url
   const Data = fetch("http://localhost:8080/orders/${o.id}").then((response) => response.json());

   const fetchOptions = {
     method: "PUT",
     headers: {
      // [csrfHeaderName] : csrfHeaderValue,///====>change header with main
       "Content-Type" : "application/json",
       "Accept" :"application/json"
     },
     body: Data
   }

  try{
    const response = await fetch(url, fetchOptions);
    } catch (error) {
    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
      }
 }

 return response.json;

      }

      async function FinishedOrders({url,dat}){
      const url = "http://localhost:8080/drivers/{id}/currentOrder" //find a way to point the principal id or to remove the id from the url
        const Data = fetch("http://localhost:8080/drivers/{id}/currentOrder").then((response) => response.json());

        const fetchOptions = {
          method: "PUT",
          headers: {
           // [csrfHeaderName] : csrfHeaderValue,///====>change header with main
            "Content-Type" : "application/json",
            "Accept" :"application/json"
          },
          body: Data
        }

        try{
          const response = await fetch(url, fetchOptions);
          } catch (error) {
          if (!response.ok) {
              const errorMessage = await response.text();
              throw new Error(errorMessage);
            }
       }}

       //post for approved order
       async function ShowApprovedOrder({url,dat}){
             const url = "http://localhost:8080/users/clients/{id}/orders" //find a way to point the principal id or to remove the id from the url
               const Data = fetch("http://localhost:8080/drivers/{id}/currentOrder").then((response) => response.json());

                const fetchOptions = {
                         method: "POST",
                         headers: {
                           //[csrfHeaderName] : csrfHeaderValue,///====>change header with main
                           "Content-Type" : "application/json",
                           "Accept" :"application/json"
                         },
                         body: Data
                       }

                       try{
                         const response = await fetch(url, fetchOptions);
                         } catch (error) {
                         if (!response.ok) {
                             const errorMessage = await response.text();
                             throw new Error(errorMessage);
                           }
                      }}
       }


