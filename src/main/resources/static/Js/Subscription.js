
const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;


    const subscriptionForm = document.getElementById('subscriptionForm')
    orderForm.addEventListener("submit",createSubscription)

    async function createSubscription(event){
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;
    const formData = new FormData(form);

    const responseData= await postFormDataAsJson({url, formData});

    //form.reset();
    }

 //=============================================================================


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

//form.reset();
  return response.json();





