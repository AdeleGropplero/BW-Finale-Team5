import { Button, Container, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { createUser } from "../redux/actions/utenteAction";

const Homepage = () => {
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();

    const registrazione = {
      username: e.target.elements.username.value,
      nome: e.target.elements.nome.value,
      cognome: e.target.elements.cognome.value,
      email: e.target.elements.email.value,
      password: e.target.elements.password.value
    };

    dispatch(createUser(registrazione));
  };

  return (
    <>
      <Container className="mt-5 my-5">
        <h1 className="mb-4">Registrazione nuovo utente</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Username</Form.Label>
            <Form.Control id="username" type="text" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Nome</Form.Label>
            <Form.Control id="nome" type="text" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Cognome</Form.Label>
            <Form.Control id="cognome" type="text" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Email</Form.Label>
            <Form.Control id="email" type="email" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Password</Form.Label>
            <Form.Control id="password" type="password" required />
          </Form.Group>

          <Button variant="primary" type="submit">
            Registrazione
          </Button>
        </Form>
      </Container>
    </>
  );
};

export default Homepage;
