const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;

const driverId
= document.getElementById('driversId').value
//const subId
//= document.getElementById('subId').value

const orderForm = document.getElementById('assignForm')
    orderForm.addEventListener("submit",Assign)

    async function Assign(event){
    event.preventDefault();

    const form = event.currentTarget;
    const url = `http://localhost:8080/api/drivers/${driverId}/SubscriptionTasks`;
    const formData = new FormData(form);

    const responseData= await putFormDataAsJson({url, formData});
console.log('going to assign subscription');


async function putFormDataAsJson({url, formData}) {

    const plainFormData = Object.fromEntries(formData.entries());
  const formDataAsJSONString = JSON.stringify(plainFormData);

  const fetchOptions = {
    method: "PUT",
              headers: {
                [csrfHeaderName] : csrfHeaderValue,
                "Content-Type" : "application/json",
                "Accept" :"application/json"
              },
              body: formDataAsJSONString
                }
      const response = await fetch(url, fetchOptions);



      form.reset();
        return response.json();
      }
      }
