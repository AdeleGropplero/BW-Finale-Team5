export const ADD_CUSTOMER = "ADD_CUSTOMER";

//aggiornamento del database con i nuovi clienti
export const addCustomer = (newCustomer) => ({
  type: ADD_CUSTOMER,
  payload: newCustomer
});

// fetch per inserire un nuovo cliente
export const createCustomer = (customer) => {
  return async (dispatch) => {
    try {
      const response = await fetch("http://localhost:8080/cliente/nuovo", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(customer)
      });

      if (response.ok) {
        const newCustomer = await response.json();
        dispatch(addCustomer(newCustomer));
      } else {
        console.log("Errore nella registrazione del cliente");
        return null;
      }
    } catch (error) {
      console.log("Errore nella registrazione del cliente", error);
      return null;
    }
  };
};
