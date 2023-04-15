const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;

const driverId
= document.getElementById('driverId').value


  const OrderDetails=document.getElementById('orderTask');
    const OrderList = [];


    const displayOrders = (orders) => {
      OrderDetails.innerHTML = orders.map(
          (o)=> {

            return singleOrder(o)
          }
      ).join('')
    }


    function singleOrder(o) {
               let orderHtml =  ` <div   id="orderDetails-o.id" class="mt-3 rounded badge-info p-3">`

                    orderHtml+=  `<span class="p-3" >From  ${o.addressFrom}</span>`
                     orderHtml+= `<span class="p-3" >To  ${o.addressTo}</span>`

                         orderHtml+= `<button type="submit" class="button" id="${o.id}" data-id=${o.id}>Take task</button>`
         orderHtml+=`</div>`
return orderHtml;
                   }


                   fetch(`http://localhost:8080/api/orders`).
                     then(response => response.json()).
                     then(data=> {

                               for(let task of data){
                               OrderList.push(task)

                               }
                                   displayOrders(OrderList)
                                   var tags = document.querySelectorAll(".button");
                                   for (var i = 0; i < tags.length; i++) {
                                    tags.item(i).addEventListener('click', Action);
                                   }
                                 })





     function Action(event){
     event.preventDefault();
                         let orderId =event.target.dataset.id;

                           AssignTask(orderId)
                        }

  async function AssignTask(orderId){

      const url = `http://localhost:8080/api/drivers/${driverId}/currentOrder`;
      const Data =  await fetch(`http://localhost:8080/api/orders/${orderId}`);



        const responseData= await putDataAsJson({url, Data});


     async function putDataAsJson({url, Data}){
     const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
     const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;



   const json = await Data.json();


      const fetchOptions = {
           method: "PUT",
           headers: {
             [csrfHeaderName] : csrfHeaderValue,
             "Content-Type" : "application/json",
             "Accept" :"application/json"
           },
           body: JSON.stringify(json)
         }
const response = await fetch(url, fetchOptions);

          return response.json;

      }
       fetch(`http://localhost:8080/api/drivers/${driverId}/currentOrder`).
             then(
      response => response.json().
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


                      document.getElementById("done").addEventListener('click',Finish) }))}



 function Finish(event){
     event.preventDefault();
                 const CurrentOrder=document.getElementById('task')

                                     CurrentOrder.innerHTML=
                                     ` <div id="task" class="mt-3 rounded badge-info p-3">

                                       <span class="p-3" >...</span>
                                       <span class="p-3" >...</span>`

                           FinishedOrders()
                        }




async function FinishedOrders(){
     const url = `http://localhost:8080/api/drivers/${driverId}/ordersList`
       const Data =await fetch(`http://localhost:8080/api/drivers/${driverId}/currentOrder`);

       const responseData= await putDataAsJson({url, Data});

       async function putDataAsJson({url, Data}){
           const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
           const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;



         const json = await Data.json();


            const fetchOptions = {
                 method: "PUT",
                 headers: {
                   [csrfHeaderName] : csrfHeaderValue,
                   "Content-Type" : "application/json",
                   "Accept" :"application/json"
                 },
                 body: JSON.stringify(json)
               }
      const response = await fetch(url, fetchOptions);

                return response.json;

            }





       }