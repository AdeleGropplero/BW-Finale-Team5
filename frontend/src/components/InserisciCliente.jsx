import { useState } from "react";
import { Button, Container } from "react-bootstrap";
import Form from "react-bootstrap/Form";
import { data } from "react-router";

const InserisciCliente = () => {
  const [formData, setFormData] = useState({
    partitaIva: "",
    email: "",
    dataInserimento: "",
    dataUltimoContatto: "",
    fatturatoAnnuale: "",
    pec: "",
    telefono: "",
    emailContatto: "",
    nomeContatto: "",
    cognomeContatto: "",
    telefonoContatto: "",
    logoAziendale: ""
  });

  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);

  const sendData = async (data) => {
    setIsLoading(true);
    setError(null);
    setSuccess(false);

    try {
      const response = await fetch("http://localhost:8080/cliente/nuovo", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
      });

      if (!response.ok) {
        throw new Error("Errore durante l'invio dei dati.");
      }

      setSuccess(true);
      setFormData({
        partitaIva: "",
        email: "",
        dataInserimento: "",
        dataUltimoContatto: "",
        fatturatoAnnuale: "",
        pec: "",
        telefono: "",
        emailContatto: "",
        nomeContatto: "",
        cognomeContatto: "",
        telefonoContatto: "",
        logoAziendale: ""
      });
    } catch (error) {
      setError(error.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    sendData(formData);
    console.log("Dati inviati:", formData);
  };
  return (
    <>
      <Container className="mt-5 my-5">
        <h1 className="mb-4">Inserimento nuovo cliente</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Partita IVA</Form.Label>
            <Form.Control type="text" name="partitaIva" value={formData.partitaIva} onChange={handleChange} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Email</Form.Label>
            <Form.Control type="email" name="email" value={formData.email} onChange={handleChange} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Data Inserimento</Form.Label>
            <Form.Control type="date" name="dataInserimento" value={formData.dataInserimento} onChange={handleChange} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Data Ultimo Contatto</Form.Label>
            <Form.Control type="date" name="dataUltimoContatto" value={formData.dataUltimoContatto} onChange={handleChange} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Fatturato Annuale (€)</Form.Label>
            <Form.Control type="number" name="fatturatoAnnuale" value={formData.fatturatoAnnuale} onChange={handleChange} step="0.01" />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>PEC</Form.Label>
            <Form.Control type="email" name="pec" value={formData.pec} onChange={handleChange} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Telefono</Form.Label>
            <Form.Control type="tel" name="telefono" value={formData.telefono} onChange={handleChange} />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Email Contatto</Form.Label>
            <Form.Control type="email" name="emailContatto" value={formData.emailContatto} onChange={handleChange} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Nome Contatto</Form.Label>
            <Form.Control type="text" name="nomeContatto" value={formData.nomeContatto} onChange={handleChange} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Cognome Contatto</Form.Label>
            <Form.Control type="text" name="cognomeContatto" value={formData.cognomeContatto} onChange={handleChange} required />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Telefono Contatto</Form.Label>
            <Form.Control type="tel" name="telefonoContatto" value={formData.telefonoContatto} onChange={handleChange} />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Logo Aziendale (URL)</Form.Label>
            <Form.Control type="text" name="logoAziendale" value={formData.logoAziendale} onChange={handleChange} />
          </Form.Group>

          <Button variant="primary" type="submit">
            Invia
          </Button>
        </Form>
      </Container>
    </>
  );
};

export default InserisciCliente;
