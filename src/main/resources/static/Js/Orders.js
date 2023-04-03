


const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;


    const orderForm = document.getElementById('orderForm')
    orderForm.addEventListener("submit",createOrder)

    async function createOrder(event){
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;
    const formData = new FormData(form);

    const responseData= await postFormDataAsJson({url, formData});
console.log('going to add order')
    //form.reset();
    }



//===================================================================================

async function postFormDataAsJson({url, formData}) {

    const plainFormData = Object.fromEntries(formData.entries());
  const formDataAsJSONString = JSON.stringify(plainFormData);

  const fetchOptions = {
    method: "POST",
    headers: {
      [csrfHeaderName] : csrfHeaderValue,
      "Content-Type" : "application/json",
      "Accept" :"application/json"
    },
    body: formDataAsJSONString
  }

  const response = await fetch(url, fetchOptions);
  //} catch (error) {
  if (!response.ok) {
      const errorMessage = await response.text("wrong response");
      throw new Error(errorMessage);
    }

//  let errorObj = JSON.parse(error.message);
//
//                       if (errorObj.fieldWithErrors) {
//                         errorObj.fieldWithErrors.forEach(
//                             e => {
//                               let elementWithError = document.getElementById(e);
//                               if (elementWithError) {
//                                 elementWithError.classList.add("is-invalid");
//                               }
//                             }
//
//                         )
//                       }
//
//                     }



//form.reset();// check if it is ok here!!!
  return response.json();
}

//fetch("http://localhost:8080/users/clients/orders").
//  then(response => response.json()).
//  then(order => {
//
//    const CurrentOrder=document.getElementById(`task`)
//
//CurrentOrder.innerHTML=`
//    <div>
//      <div class="text-center">
//          <p class="from">|Adress From: ${order.addressFrom}|</p>
//          <p class="to">|Address To: ${order.addressTo}|</p>
//          <p> class="accepted">Order accepted<p>
//          <p> class="cost"><p>
//      </div>
//  </div>`
//
//  })
//sending to url users/clients/order new json for order which should be as the binding model

//====================================================================================
