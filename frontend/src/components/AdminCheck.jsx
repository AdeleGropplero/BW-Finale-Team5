import { Button, Container } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { adminGetFatture } from "../redux/actions/loginAction";

const AdminCheck = () => {
  const dispatch = useDispatch();

  const getListaFatture = () => {
    dispatch(adminGetFatture());
  };

  return (
    <>
      <Container className="mt-5 my-5">
        <Button variant="success" onClick={getListaFatture}>
          Lista fatture
        </Button>
      </Container>
    </>
  );
};

export default AdminCheck;
