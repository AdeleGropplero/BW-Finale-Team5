export const ADD_FATTURA = "ADD_FATTURA";

//aggiornamento del database con le nuove fatture
export const addFattura = (newFattura) => ({
  type: ADD_FATTURA,
  payload: newFattura
});

// fetch per inserire una nuova fattura
export const createFattura = (fattura, idCliente) => {
  return async (dispatch) => {
    try {
      const response = await fetch("http://localhost:8080/cliente/addFattura/" + idCliente, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(fattura)
      });

      if (response.ok) {
        const newFattura = await response.json();
        dispatch(addFattura(newFattura));
      } else {
        console.log("Errore nella registrazione della fattura");
        return null;
      }
    } catch (error) {
      console.log("Errore nella registrazione della fattura", error);
      return null;
    }
  };
};
