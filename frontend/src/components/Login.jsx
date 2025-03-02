import { Button, Container } from "react-bootstrap";
import Form from "react-bootstrap/Form";
import { useDispatch } from "react-redux";
import { fetchLogin } from "../redux/actions/loginAction";

const Login = () => {
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();

    const loginInfo = {
      username: e.target.elements.username.value,
      password: e.target.elements.password.value
    };

    dispatch(fetchLogin(loginInfo));
  };

  return (
    <>
      <Container className="mt-5 my-5">
        <h1 className="mb-4">Login 👮‍♂️</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Username</Form.Label>
            <Form.Control id="username" type="text" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Password</Form.Label>
            <Form.Control id="password" type="password" required />
          </Form.Group>

          <Button variant="success" type="submit">
            Login
          </Button>
        </Form>
      </Container>
    </>
  );
};

export default Login;
