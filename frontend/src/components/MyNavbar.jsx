import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";
import { Link } from "react-router";

function MyNavbar() {
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">EpicEnergy</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link>
              <Link to="/utente" className="text-decoration-none text-dark">
                Utenti
              </Link>
            </Nav.Link>

            <Nav.Link>
              <Link to="/cliente" className="text-decoration-none text-dark">
                Clienti
              </Link>
            </Nav.Link>

            <Nav.Link>
              <Link to="/fattura" className="text-decoration-none text-dark">
                Fatture
              </Link>
            </Nav.Link>

            <NavDropdown title="Login" id="basic-nav-dropdown">
              <NavDropdown.Item>
                <Link to="/" className="text-decoration-none text-dark">
                  Login
                </Link>
              </NavDropdown.Item>
              <NavDropdown.Item>Info fatture</NavDropdown.Item>
              <NavDropdown.Item>Info clienti</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">Exit</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default MyNavbar;
