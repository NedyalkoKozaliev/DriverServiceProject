





const orderForm = document.getElementById('orderForm')
orderForm.addEventListener("submit",postFormDataAsJson)



//=============================Posting data from html form to given url with the response.json==============
 // ========================cotroller reading it making something with and giving response with==============

async function postFormDataAsJson({url, formData}) { ///==>taking data from form , putting url and formdata directly here

const form = event.currentTarget;
    //const url="/users/clients/orders";
 const url = form.action;
  const formData = new FormData(form);

    const plainFormData = Object.fromEntries(formData.entries());
  const formDataAsJSONString = JSON.stringify(plainFormData);
  //https://www.section.io/engineering-education/how-to-format-form-data-as-json/

  const fetchOptions = {
    method: "POST",
    headers: {
     // [csrfHeaderName] : csrfHeaderValue,///====>change header with main
      "Content-Type" : "application/json",
      "Accept" :"application/json"
    },
    body: formDataAsJSONString
  }
  try{
  const response = await fetch(url, fetchOptions);
  } catch (error) {
  if (!response.ok) {
      const errorMessage = await response.text();
      throw new Error(errorMessage);
    }

  let errorObj = JSON.parse(error.message);

                       if (errorObj.fieldWithErrors) {
                         errorObj.fieldWithErrors.forEach(
                             e => {
                               let elementWithError = document.getElementById(e);
                               if (elementWithError) {
                                 elementWithError.classList.add("is-invalid");
                               }
                             }

                         )
                       }

                     }



form.reset();// check if it is ok here!!!
  return response.json(); //==>returning response to controller ?
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
