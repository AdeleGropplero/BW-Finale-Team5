import { Button, Container, Nav, Navbar, NavDropdown } from "react-bootstrap";

function MyNavbar() {
  const firstFetch = () => {
    fetch("http://localhost:8080/cliente/clientiAZ")
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

  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="#home">Home</Nav.Link>
            <Nav.Link href="#link">Link</Nav.Link>

            <Button onClick={firstFetch}>Bottone</Button>

            <NavDropdown title="Dropdown" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default MyNavbar;
