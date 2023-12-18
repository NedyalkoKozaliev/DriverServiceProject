

const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;


    const orderForm = document.getElementById('emailForm')
    orderForm.addEventListener("submit",changeEmail)

    async function changeEmail(event){
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;
    const formData = new FormData(form);
try{
    const responseData= await putFormDataAsJson({url, formData});

    form.reset();
    }catch(error){
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
    }

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
 if (!response.ok) {
      const errorMessage = await response.text();
      throw new Error(errorMessage);
    }



        return response.json();
            }