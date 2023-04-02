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
   //=====================================Single order displaying ============================================


    function singleOrder(o) {
      let orderHtml = `<label th:object=o  id="orderDetails-${o.Id}">`
      orderHtml +=`"From:"${o.addressFrom } "To:"${o.addressTo}"`
      orderHtml += `<button  class="btn btn-outline-secondary" type="button" id="task - ${o.id}">Take task</button>`
      orderHtml += `</label>`
    const takeTaskButton=document.getElementById('task - ${o.id}')
    takeTaskButton.dataset.id=orderTask.id
    takeTaskButton.addEventListener('click', AssignTask())

      return orderHtml
    }
//======================================Fetching data ==========================================================

  fetch(`http://localhost:8080/api/orders`).
  then(response => response.json()).
  then(orderTask => {


         OrderList.push(orderTask)

                displayOrders(OrderList)
              })


    }

//====================================================================================================================

//##############################################################################################

//==========================================Driver take task=======================================================


      function AssignTask(event) {
        event.preventDefault();

     let task = document.getElementById('orderDetails-${o.Id}');
    if (task.parentNode) {
      task.parentNode.removeChild(task);
    }
    fetch(`http://localhost:8080/api/orders`). ///трябва да го променя , за да не се покаже поръчката
        then(_ => DisplayOrderTasks()).
        catch(error => console.log('error', error))

        ShowApprovedOrder();
        AssignOrderToDriver();

}

//==================================>Show Approved order <================================================

    async function ShowApprovedOrder({url,dat}){
             const url = `http://localhost:8080/api/clients/{id}(id=${o.getClient()})` //find a way to point the principal id or to remove the id from the url
               const Data = fetch(`http://localhost:8080/api/drivers/${driverId}/currentOrder`).then((response) => response.json());

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

//=======================================================> Assign Order To Driver <====================================

 async function AssignOrderToDriver({url, Data}) {

  const url = `http://localhost:8080/api/drivers/${driverId}` //find a way to point the principal id or to remove the id from the url
   const Data = fetch(`http://localhost:8080/api/orders/${o.id}`).then((response) => response.json());

   const fetchOptions = {
     method: "PUT",
     headers: {
       [csrfHeaderName] : csrfHeaderValue,///====>change header with main
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

//#######################################################################

//=========================================> Order in Process <===================================================================

async function OrderProcessing(){
fetch(`http://localhost:8080/api/drivers/${driverId}/currentOrder`).
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
  document.getElementById("done").addEventListener('click',FinishedOrders) }

//===============================================>Order finishing<============================================================

      async function FinishedOrders({url,dat}){
      const url = `http://localhost:8080/api/drivers/${driverId}/ordersList` //find a way to point the principal id or to remove the id from the url
        const Data = fetch(`http://localhost:8080/api/drivers/${driverId}/currentTask`).then((response) => response.json());

        const fetchOptions = {
          method: "PUT",
          headers: {
            [csrfHeaderName] : csrfHeaderValue,///====>change header with main
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





