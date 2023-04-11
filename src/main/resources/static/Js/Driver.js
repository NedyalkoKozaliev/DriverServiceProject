//const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
//const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;
const driverId = document.getElementById('driverId').value


  const OrderDetails=document.getElementById('orderTask');
    const OrderList = [];


    const displayOrders = (orders,AssignTask) => {
      OrderDetails.innerHTML = orders.map(
          (o)=> {

            return singleOrder(o)
          }
      ).join('')
    }
   //=====================================Single order displaying ============================================


    function singleOrder(o) {
           let orderHtml =  ` <div   id="orderDetails-o.Id" class="mt-3 rounded badge-info p-3">

                  <span class="p-3" >From  ${o.addressFrom}</span>
                  <span class="p-3" >To  ${o.addressTo}</span>
<button id="btn-${o.Id}" onclick="AssignTask()" data-id="${o.Id}">jskjdjsj</button>
               </div>`


  async function AssignTask(){
  const url = `http://localhost:8080/api/drivers/${driverId}/currentOrder`;
                    const Data = fetch(`http://localhost:8080/api/orders/${o.Id}`).then((response) => response.json());

   const responseData= await putDataAsJson({url, Data});

}

//let actButton=document.createElement('button')
//actButton.innerText='Take the task'
//actButton.dataset.id=o.Id
//actButton.addEventListener('click',AssignTask)
//const parentDOM=orderHtml;
//parentDOM.a

//<button onclick="AssignTask(this)" class="task_take" type="button" id="task - ${o.id}" name="task_take" data-id="${o.id}" >Take the task</button>

//let deleteBtn = document.createElement('button')
//                     deleteBtn.innerText = 'DELETE'
//                     deleteBtn.dataset.id = book.id
//                     deleteBtn.addEventListener('click', deleteBtnClicked)

  //              <input type="button"
//                                              class="btn"
//                                             id="take-${o.id}"
//                                             value="Take the task"/>


      return orderHtml;
    }
//======================================Fetching data ==========================================================


  fetch(`http://localhost:8080/api/orders`).
  then(response => response.json()).
  then(data=> {

            for(let task of data){
            OrderList.push(task)

            }
                displayOrders(OrderList)
              })

//const button=document.getElementById('take-${o.id}');
//button.dataset.id=o.id;
//    button.addEventListener('click', AssignTask());


//====================================================================================================================

//##############################################################################################

//==========================================Driver take task=======================================================

//      async function AssignTask(event) {
//        event.preventDefault();
//
//     let task = document.getElementById(`orderDetails-${o.Id}`);
//    if (task.parentNode) {
//      task.parentNode.removeChild(task);
//    }
////    fetch(`http://localhost:8080/api/orders`).
////        then(_ => DisplayOrderTasks()).
////        catch(error => console.log('error', error))
//
//         AssignOrderToDriver();
//}

//function AssignOrderTask(){
//let OrderId=target.dataset.id;
//AssignTask(OrderId)
//}
////=======================================================> Assign Order To Driver <====================================
//function BtnClicked(event) {
//  let orderId = event.target.dataset.id;
//
//  deleteBook(bookId)
//}




// async function AssignTask(){
//
//
//
//
////let task = document.getElementById(`orderDetails-${o.id}`);
////    if (task.parentNode) {
////      task.parentNode.removeChild(task);(id=${driver})
////    }  /api/{routeId}/comments(routeId=${route.id})
//
//const url = `http://localhost:8080/api/drivers/${driverId}/currentOrder`;
//   const Data = fetch(`http://localhost:8080/api/orders/${o.Id}`).then((response) => response.json());
//
//   const responseData= await putDataAsJson({url, Data});
//
//}
//{id}(id=${o.id})

 async function putDataAsJson({url, Data}){
const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;
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






