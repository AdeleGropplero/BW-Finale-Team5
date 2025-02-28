import { ADD_FATTURA } from "../actions/fatturaAction";

const initialState = {
  content: []
};

const fatturaReducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_FATTURA:
      return {
        ...state,
        content: action.payload
      };
    default:
      return state;
  }
};

export default fatturaReducer;
