import { Button, Container } from "react-bootstrap";
import Form from "react-bootstrap/Form";
import { useDispatch } from "react-redux";
import { createCustomer } from "../redux/actions/clienteAction";

const Cliente = () => {
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();

    const registrazione = {
      partitaIva: e.target.elements.partitaIva.value,
      email: e.target.elements.email.value,
      dataInserimento: e.target.elements.dataInserimento.value,
      dataUltimoContatto: e.target.elements.dataUltimoContatto.value,
      fatturatoAnnuale: e.target.elements.fatturatoAnnuale.value,
      pec: e.target.elements.pec.value,
      telefono: e.target.elements.telefono.value,
      emailContatto: e.target.elements.emailContatto.value,
      nomeContatto: e.target.elements.nomeContatto.value,
      cognomeContatto: e.target.elements.cognomeContatto.value,
      telefonoContatto: e.target.elements.telefonoContatto.value
    };

    dispatch(createCustomer(registrazione));
  };

  return (
    <>
      <Container className="mt-5 my-5">
        <h1 className="mb-4">Registrazione nuovo cliente</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Partita IVA</Form.Label>
            <Form.Control id="partitaIva" type="text" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Email</Form.Label>
            <Form.Control id="email" type="email" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Data Inserimento</Form.Label>
            <Form.Control id="dataInserimento" type="date" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Data Ultimo Contatto</Form.Label>
            <Form.Control id="dataUltimoContatto" type="date" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Fatturato Annuale (€)</Form.Label>
            <Form.Control id="fatturatoAnnuale" type="number" step="0.01" />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>PEC</Form.Label>
            <Form.Control id="pec" type="email" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Telefono</Form.Label>
            <Form.Control id="telefono" type="tel" />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Email Contatto</Form.Label>
            <Form.Control id="emailContatto" type="email" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Nome Contatto</Form.Label>
            <Form.Control id="nomeContatto" type="text" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Cognome Contatto</Form.Label>
            <Form.Control id="cognomeContatto" type="text" required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Telefono Contatto</Form.Label>
            <Form.Control id="telefonoContatto" type="tel" />
          </Form.Group>

          <Button variant="primary" type="submit">
            Registrazione
          </Button>
        </Form>
      </Container>
    </>
  );
};

export default Cliente;
