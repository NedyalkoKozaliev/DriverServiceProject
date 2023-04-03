const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;


async function DisplayOrderTasks(){

  const OrderDetails=document.getElementById('orderTask')
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
      let orderHtml = `<label th:object="${o}" id="orderDetails-${o.id}">`
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
//    fetch(`http://localhost:8080/api/orders`). ///трябва да го променя , за да не се покаже поръчката
//        then(_ => DisplayOrderTasks()).
//        catch(error => console.log('error', error))

         AssignOrderToDriver();
         ShowApprovedOrder();


}

//==================================>Show Approved order <================================================

//async function ShowApprovedOrder(){
////const url = `http://localhost:8080/api/clients/{id}(id=${o.getClient()})` //find a way to point the principal id or to remove the id from the url
//    const url = `http://localhost:8080/api/orders/{id}/done(id=${o.getClient()})`
//    const Data = fetch(`http://localhost:8080/api/drivers/${driverId}/currentOrder`).then((response) => response.json());
//
//const responseData= await postDataAsJson({url, Data});
//} //===>да пробвам от драйвър кърънт ордър , даи поръчката на клиента не е в някой от тях и ако е се показва

async function postDataAsJson({url, Data}) {



   const fetchOptions = {
     method: "POST",
     headers: {
       [csrfHeaderName] : csrfHeaderValue,
       "Content-Type" : "application/json",
       "Accept" :"application/json"
     },
     body: Data
   }

   const response = await fetch(url, fetchOptions);

   if (!response.ok) {
       const errorMessage = await response.text("wrong response");
       throw new Error(errorMessage);
     }
      return response.json();
      }

//    async function ShowApprovedOrder({url,data}){
//             const url = `http://localhost:8080/api/clients/{id}(id=${o.getClient()})` //find a way to point the principal id or to remove the id from the url
//               const Data = fetch(`http://localhost:8080/api/drivers/${driverId}/currentOrder`).then((response) => response.json());
//
//                const fetchOptions = {
//                         method: "POST",
//                         headers: {
//                           [csrfHeaderName] : csrfHeaderValue,///====>change header with main
//                           "Content-Type" : "application/json",
//                           "Accept" :"application/json"
//                         },
//                         body: Data
//                       }
//
//                       try{
//                         const response = await fetch(url, fetchOptions);
//                         } catch (error) {
//                         if (!response.ok) {
//                             const errorMessage = await response.text();
//                             throw new Error(errorMessage);
//                           }
//                      }}

//=======================================================> Assign Order To Driver <====================================
async function AssignOrderToDriver(){
const url = `http://localhost:8080/api/drivers/${driverId}`
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

//
// async function AssignOrderToDriver({url, Data}) {
//
//  const url = `http://localhost:8080/api/drivers/${driverId}`
//   const Data = fetch(`http://localhost:8080/api/orders/${o.id}`).then((response) => response.json());
//
//   const fetchOptions = {
//     method: "PUT",
//     headers: {
//       [csrfHeaderName] : csrfHeaderValue,
//       "Content-Type" : "application/json",
//       "Accept" :"application/json"
//     },
//     body: Data
//   }
//
//  try{
//    const response = await fetch(url, fetchOptions);
//    } catch (error) {
//    if (!response.ok) {
//        const errorMessage = await response.text();
//        throw new Error(errorMessage);
//      }
// }
//
// return response.json;
//
//      }

//#######################################################################

//=========================================> Order in Process <===================================================================

async function OrderProcessing(){
fetch(`http://localhost:8080/api/drivers/${driverId}/currentOrder`).
  then(response => response.json()).
  then(order => {

    const CurrentOrder=document.getElementById('task')

CurrentOrder.innerHTML=`
    <div>
      <div class="text-center">
          <p class="from">Address From: ${order.addressFrom}</p>
          <p class="to">Address To: ${order.addressTo}</p>
      </div>
      <div class="btn-group">
          <a  id="done">Done</a>
      </div>
  </div>`

  })
  document.getElementById("done").addEventListener('click',FinishedOrders) }

//===============================================>Order finishing<============================================================

//      async function FinishedOrders(){
//      const url = `http://localhost:8080/api/drivers/${driverId}/ordersList`
//        const Data = fetch(`http://localhost:8080/api/drivers/${driverId}/currentTask`).then((response) => response.json());
//
//        const responseData= await putDataAsJson({url, Data});
//        }

//        const fetchOptions = {
//          method: "PUT",
//          headers: {
//            [csrfHeaderName] : csrfHeaderValue,
//            "Content-Type" : "application/json",
//            "Accept" :"application/json"
//          },
//          body: Data
//        }
//
//        try{
//          const response = await fetch(url, fetchOptions);
//          } catch (error) {
//          if (!response.ok) {
//              const errorMessage = await response.text();
//              throw new Error(errorMessage);
//            }
//       }}





