export const ADD_USER = "ADD_USER";

// aggiornamento del database con i nuovi utenti
export const addUser = (newUser) => ({
  type: ADD_USER,
  payload: newUser
});

// fetch per inserire un nuovo utente
export const createUser = (user) => {
  return async (dispatch) => {
    try {
      const response = await fetch("http://localhost:8080/utente/registrazione", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
      });

      if (response.ok) {
        const newUser = await response.json();
        dispatch(addUser(newUser));
      } else {
        console.log("Errore nella registrazione dell'utente");
        return null;
      }
    } catch (error) {
      console.log("Errore nella registrazione dell'utente", error);
      return null;
    }
  };
};
