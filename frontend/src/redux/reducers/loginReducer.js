import { CHECK_LOGIN, LISTA_FATTURE } from "../actions/loginAction";

const initialState = {
  listaFatture: [],
  token: null
};

const loginReducer = (state = initialState, action) => {
  switch (action.type) {
    case CHECK_LOGIN:
      return { ...state, token: action.payload };
    case LISTA_FATTURE:
      return {
        ...state,
        listaFatture: action.payload
      };
    default:
      return state;
  }
};

export default loginReducer;
