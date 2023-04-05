const adminId = document.getElementById('adminId').value;

const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;


async function DisplaySubscriptions(){

  const SubscriptionDetails=document.getElementById('AssignDriver')
    const SubscriptionList = []
///////////////////////////////////////////////

    const displaySubscription = (subscriptions) => {
        SubscriptionDetails.innerHTML = subscriptions.map(
          (s)=> {
            return singleSubscription(s)
          }
      ).join('')
    }

function singleOrder(s) {
let subsHtml =  ` <div id="subscription_task"   id="subscriptionDetails-${s.id}" class="mt-3 rounded badge-info p-3">
       <span class="p-3" th:text="|Type : ${s.subscription.name}"|>....</span>
      <span class="p-3" th:text="|From : ${s.addressFrom}"|>....</span>
      <span class="p-3"  th:text="|To : ${s.addressTo}"|>.....</span>

      <div class="btn-group">

      <form id="assignForm" method="post"

      class="main-form mx-auto col-md-8 d-flex flex-column justify-content-center">
      <div class="form-group row">
      <label for="subscription" class="col-sm-2 col-form-label">Assign Subscription</label>
      <div class="col-sm-10">

      <select  errorclass="is-invalid" id="subscription" name="subscription" class="custom-select"
                  aria-describedby="subscriptionTypeAdd">
    <option value="" selected>Driver</option>
    <option th:each="driver : ${drivers}" th:value="${driver.email}" th:text="${driver.email}">driver</option>
    </select>
    <small id="subscriptionTypeAdd" class="invalid-feedback bg-danger text-light rounded">
    You must select the subscription.
    </small>
    </div>
    </div>
    <div class="form-group">
    <input type="submit"
                               class="btn"
                               id="assign - ${driver.id}"
                               value="Assign"/>
                               </div>
                               </form>`


const AssignButton=document.getElementById(`assign - ${driver.id}`)
    AssignButton.dataset.id=s.id
    AssignButton.addEventListener('click', AssignTask())

      return subsHtml
}

fetch(`http://localhost:8080/api/subscriptions`).
  then(response => response.json()).
  then(subscriptionTask => {


    SubscriptionList.push(subscriptionTask)

    displaySubscription(SubscriptionList)
              })


    }

function AssignTask(event) {
        event.preventDefault();

     let task = document.getElementById(`orderDetails-${o.Id}`);// да променя
    if (task.parentNode) {
      task.parentNode.removeChild(task);
    }

    AssignSubscriptionToDriver();
}

async function AssignSubscriptionToDriver(){
    const url= ` http://localhost:8080/api/drivers/${driver.id}/SubscriptionTasks`
   // const url = `http://localhost:8080/api/admins/${adminId}`
       const Data = fetch(`http://localhost:8080/api/subscription/${s.id}`).then((response) => response.json());

       const responseData= await putDataAsJson({url, Data});

    }

     async function poutDataAsJson({url, Data}){

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
