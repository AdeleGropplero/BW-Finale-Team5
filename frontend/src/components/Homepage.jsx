import { Button, Form } from "react-bootstrap";

const Homepage = () => {
  const registrazioneUtente = (registrazione) => {
    const formData = new FormData();

    //Aggiungere i file al formData
    formData.append("utente", JSON.stringify(registrazione));

    fetch("http://localhost:8080/utente/registrazione", {
      method: "POST",
      body: formData
    })
      .then((response) => {
        //console.log(response);
        if (response.ok) {
          return response.json();
        } else {
          throw new Error("Errore nella richiesta");
        }
      })
      .then((data) => data.forEach((client) => console.log(client)))
      .catch((error) => {
        console.log(error);
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const registrazione = {
      username: e.target.elements.username.value,
      nome: e.target.elements.nome.value,
      cognome: e.target.elements.cognome.value,
      email: e.target.elements.email.value,
      password: e.target.elements.password.value
    };

    registrazioneUtente(registrazione);
  };

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
          <Form.Label>Username</Form.Label>
          <Form.Control id="username" type="text" placeholder="username" />
          <Form.Label>Nome</Form.Label>
          <Form.Control id="nome" type="text" placeholder="nome" />
          <Form.Label>Cognome</Form.Label>
          <Form.Control id="cognome" type="text" placeholder="cognome" />
          <Form.Label>Email address</Form.Label>
          <Form.Control id="email" type="email" placeholder="name@example.com" />
          <Form.Label>Password</Form.Label>
          <Form.Control id="password" type="password" placeholder="password" />

          <Form.Label>Default file input example</Form.Label>
          <Form.Control id="avatar" type="file" />

          <Button type="submit">Registrazione</Button>
        </Form.Group>
      </Form>
    </>
  );
};

export default Homepage;
