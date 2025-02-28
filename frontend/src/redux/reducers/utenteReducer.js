import { ADD_USER } from "../actions/utenteAction";

const initialState = {
  content: []
};

const utenteReducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_USER:
      return {
        ...state,
        content: action.payload
      };
    default:
      return state;
  }
};

export default utenteReducer;
