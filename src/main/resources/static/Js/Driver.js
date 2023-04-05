//const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
//const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;



  const OrderDetails=document.getElementById('orderTask');
    const OrderList = [];


    const displayOrders = (orders) => {
      OrderDetails.innerHTML = orders.map(
          (o)=> {
            return singleOrder(o)
          }
      ).join('')
    }
   //=====================================Single order displaying ============================================


    function singleOrder(o) {
           let orderHtml =  ` <div id="task"  id="orderDetails-${o.id}" class="mt-3 rounded badge-info p-3">

                  <span class="p-3" >From  ${o.addressFrom}</span>
                  <span class="p-3" >To  ${o.addressTo}</span>
                  <div class="btn-group">
                <input type="button"
                                              class="btn"
                                             id="take-${o.id}"
                                             value="Take the task"/>
                </div>
               </div>`

//const takeTaskButton=document.getElementById("task - ${o.id}")
//    takeTaskButton.dataset.id=${o.id}


      return orderHtml;
    }
//======================================Fetching data ==========================================================


  fetch(`http://localhost:8080/api/orders`).
  then(response => response.json()).
  then(data=> {

            for(let task of data){
            OrderList.push(task)}
                displayOrders(OrderList)
              })

const button=document.getElementById('take-${o.id}');
button.dataset.id=o.id;
    button.addEventListener('click', AssignTask());


//====================================================================================================================

//##############################################################################################

//==========================================Driver take task=======================================================

      async function AssignTask(event) {
        event.preventDefault();

     let task = document.getElementById(`orderDetails-${o.Id}`);
    if (task.parentNode) {
      task.parentNode.removeChild(task);
    }
//    fetch(`http://localhost:8080/api/orders`).
//        then(_ => DisplayOrderTasks()).
//        catch(error => console.log('error', error))

         AssignOrderToDriver();
}


////=======================================================> Assign Order To Driver <====================================
async function AssignOrderToDriver(){
const url = `http://localhost:8080/api/drivers/${driverId}/currentOrder`;
   const Data = fetch(`http://localhost:8080/api/orders/${o.id}`).then((response) => response.json());

   const responseData= await putDataAsJson({url, Data});

}

 async function putDataAsJson({url, Data}){

 const fetchOptions = {
      method: "PUT",
      headers: {
        [csrfHeaderName] : csrfHeaderValue,
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

 //============================================Order processing ======================
async function OrderProcessing(){
fetch(`http://localhost:8080/api/drivers/${driverId}/currentOrder`).
  then(response => response.json()).
  then(order => {

    const CurrentOrder=document.getElementById('task')

CurrentOrder.innerHTML=
` <div id="task" class="mt-3 rounded badge-info p-3">

  <span class="p-3" >${order.addressFrom}</span>
  <span class="p-3" >${order.addressTo}</span>
  <div class="btn-group">

           <button class="finish_order" type="button" id="done" name="done">Done</button>
</div>
</div>`


  document.getElementById("done").addEventListener("submit",FinishedOrders()) })}

//==========================================closing order===============================================
async function FinishedOrders(){
     const url = `http://localhost:8080/api/drivers/${driverId}/ordersList`
       const Data = fetch(`http://localhost:8080/api/drivers/${driverId}/currentTask`).then((response) => response.json());

       const responseData= await putDataAsJson({url, Data});
       }






