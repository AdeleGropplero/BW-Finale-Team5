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
              <Link to="/" className="text-decoration-none text-dark">
                Home
              </Link>
            </Nav.Link>

            <Nav.Link>
              <Link to="/Cliente" className="text-decoration-none text-dark">
                Clienti
              </Link>
            </Nav.Link>

            <Nav.Link>Fatture</Nav.Link>

            <NavDropdown title="Login" id="basic-nav-dropdown">
              <NavDropdown.Item>Login</NavDropdown.Item>
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
