export const CHECK_LOGIN = "CHECK_LOGIN";
export const LISTA_FATTURE = "LISTA_FATTURE";

// metodo che riempie la lista delle fatture
export const updateListaFatture = (listaFatture) => ({
  type: LISTA_FATTURE,
  payload: listaFatture
});

//confronto i dati di login con quelli su database
export const checkLogin = (jwt) => ({
  type: CHECK_LOGIN,
  payload: jwt
});

// fetch per la restituzione della lista delle fatture da ADMIN
export const adminGetFatture = () => {
  return async (dispatch, getState) => {
    try {
      const token = getState().login.token;

      if (!token) {
        console.log("Token non presente, impossibile recuperare le fatture");
        return;
      }

      console.log("Token usato per la richiesta: ", token);

      const response = await fetch("http://localhost:8080/admin/1", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token
        }
      });

      if (response.ok) {
        const listaFatture = await response.json();
        dispatch(updateListaFatture(listaFatture));
        console.log(listaFatture);
      } else {
        console.log("Errore nel caricamento delle fatture");
        return null;
      }
    } catch (error) {
      console.log("Errore nel caricamento delle fatture", error);
      return null;
    }
  };
};

// fetch per il login
export const fetchLogin = (loginInfo) => {
  return async (dispatch) => {
    try {
      const response = await fetch("http://localhost:8080/utente/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(loginInfo)
      });

      if (response.ok) {
        const loginResponse = await response.json();
        console.log(loginResponse.token);
        dispatch(checkLogin(loginResponse.token));
      } else {
        console.log("Errore nel login dell'utente");
        return null;
      }
    } catch (error) {
      console.log("Errore nel login dell'utente", error);
      return null;
    }
  };
};
