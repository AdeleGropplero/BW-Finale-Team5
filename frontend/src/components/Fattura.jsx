import { Button, Container, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { createFattura } from "../redux/actions/fatturaAction";

const Fattura = () => {
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();

    const idCliente = e.target.elements.idCliente.value;

    const fattura = {
      dataFattura: e.target.elements.dataFattura.value,
      importo: e.target.elements.importo.value
    };

    dispatch(createFattura(fattura, idCliente));
  };

  return (
    <>
      <Container className="mt-5 my-5">
        <h1 className="mb-4">Registrazione nuova fattura 📃</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Data fattura</Form.Label>
            <Form.Control id="dataFattura" type="date" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Importo</Form.Label>
            <Form.Control id="importo" type="number" step="0.01" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Id Cliente</Form.Label>
            <Form.Control id="idCliente" type="number" placeholder="Id del cliente a cui intestare la fattura" required />
          </Form.Group>

          <Button variant="primary" type="submit">
            Registra fattura
          </Button>
        </Form>
      </Container>
    </>
  );
};

export default Fattura;
