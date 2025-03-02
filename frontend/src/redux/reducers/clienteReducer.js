import { ADD_CUSTOMER } from "../actions/clienteAction";

const initialState = {
  content: []
};

const customerReducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_CUSTOMER:
      return {
        ...state,
        content: action.payload
      };
    default:
      return state;
  }
};

export default customerReducer;
