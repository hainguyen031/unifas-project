import React, { useEffect, useState } from "react";
import Sidebar from "../../components/admin/sidebar/Sidebar";
import SettingsIcon from "@mui/icons-material/Settings";
import { useDispatch, useSelector } from "react-redux";
import {
  addNewCategory,
  getCategoriesPage,
  removeCategory,
  selectAddCategories,
  selectCategoriesPage,
  selectCategoryError,
  selectCategoryLoading,
  selectCategorySuccess,
} from "../../feature/category/categorySlice";
import { Link } from "react-router-dom";
import { Button, Form, Modal } from "react-bootstrap";
import { ToastContainer, toast } from "react-toastify";

function AdminCategory() {
  const dispatch = useDispatch();
  const listCategory = useSelector(selectCategoriesPage);
  const checkAddCategory = useSelector(selectAddCategories);
  const success = useSelector(selectCategorySuccess);
  const pending = useSelector(selectCategoryLoading);
  const error = useSelector(selectCategoryError);
  const [category, setCategory] = useState([]);
  const [isModalOpen, setModalOpen] = useState(false);
  const [reRender, setReRender] = useState(false);
  const [page, setPage] = useState(0);
  const [show, setShow] = useState(false);
  const [data, setData] = useState({
    name: "",
    gender: "",
  });
  useEffect(() => {
    if (category?.length === 0 || reRender) {
      dispatch(getCategoriesPage(page));
    }
    setCategory(listCategory);
    setReRender(false);
  }, [listCategory]);

  const handlePageChange = (e) => {
    setPage(e);
    setReRender(true);
    dispatch(getCategoriesPage(e));
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleRemoveCategory = (e) => {
    dispatch(removeCategory(e));
    setReRender(true);
    toast.success("Xóa category thành công !", {
      position: toast.POSITION.TOP_RIGHT,
      type: toast.TYPE.SUCCESS,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    dispatch(addNewCategory(data));
    if (checkAddCategory) {
      toast.success("Thêm mới thành công !", {
        position: toast.POSITION.TOP_RIGHT,
        type: toast.TYPE.SUCCESS,
      });
      setReRender(true);
      closeModal();
    } else if (error) {
      toast.error("Thêm mới thất bại !", {
        position: toast.POSITION.TOP_RIGHT,
        type: toast.TYPE.ERROR,
      });
    //   dispatch(setCategoryError(null));
    }
    // dispatch(setAddCategory(null));
  };
  
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

    const handleSaveChanges = () => {
     console.log(data);
    };

  return (
    <div className="list">
      <Sidebar />
      <div className="listContainer">
        <div className="m-5">
          <div style={{ display: "flex", justifyContent: "center" }}>
            <h2>List category</h2>
            <button
              type="button"
              className="btn"
              style={{ width: "auto", left: "25%", position: "relative" }}
              onClick={openModal}
            >
              + New Category
            </button>
            <Modal show={isModalOpen} onHide={closeModal}>
              <Modal.Header>
                <Modal.Title>ADD NEW CATEGORY</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <>
                  <div className="input-group mb-3">
                    <span
                      className="input-group-text"
                      id="inputGroup-sizing-default"
                    >
                      NAME
                    </span>
                    <input
                      type="text"
                      name="name"
                      className="form-control"
                      aria-label="Sizing example input"
                      aria-describedby="inputGroup-sizing-default"
                      onChange={handleInputChange}
                    />
                  </div>
                  <div className="input-group mb-3" style={{ display: "flex" }}>
                    <span
                      className="input-group-text"
                      id="inputGroup-sizing-default"
                    >
                      GENDER
                    </span>
                    <select
                      className="form-select "
                      name="gender"
                      aria-label="Default select example"
                      style={{ width: "fit-content" }}
                      onChange={handleInputChange}
                    >
                      <option selected="">Open this select menu</option>
                      <option value={"WOMEN"}>WOMEN</option>
                      <option value={"MEN"}>MEN</option>
                      <option value={"KIDS"}>KIDS</option>
                      <option value={"BABY"}>BABY</option>
                    </select>
                  </div>
                </>
              </Modal.Body>
              <Modal.Footer style={{ justifyContent: "center" }}>
                <Button
                  variant="secondary"
                  onClick={closeModal}
                  style={{ justifyContent: "center" }}
                >
                  Close
                </Button>
                <Button
                  variant="primary"
                  onClick={handleSubmit}
                  style={{ justifyContent: "center" }}
                >
                  Add new
                </Button>
              </Modal.Footer>
            </Modal>
          </div>
          <table className="table table-hover" style={{ marginTop: 10 }}>
            <thead>
              <tr>
                <th>STT</th>
                <th>NAME</th>
                <th>GENDER</th>
                <th>
                  <SettingsIcon></SettingsIcon>
                </th>
              </tr>
            </thead>
            <tbody>
              {category?.content?.map((item) => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.name}</td>
                  <td>{item.gender}</td>
                  <td>
                    <Link className="m-2" href="#" onClick={handleShow}>
                      <i className="fas fa-edit text-primary"></i>
                    </Link>
                    <Link
                      className="m-2"
                      href="#"
                      onClick={() => handleRemoveCategory(item.id)}
                    >
                      <i className="fas fa-trash text-danger"></i>
                    </Link>
                    <Modal show={show} onHide={handleClose}>
                      <Modal.Header>
                        <Modal.Title>Edit category</Modal.Title>
                      </Modal.Header>
                      <Modal.Body>
                        <>
                          <div className="input-group mb-3">
                            <span
                              className="input-group-text"
                              id="inputGroup-sizing-default"
                            >
                              NAME
                            </span>
                            <input
                              type="text"
                              name="name"
                              className="form-control"
                              aria-label="Sizing example input"
                              aria-describedby="inputGroup-sizing-default"
                              onChange={handleInputChange}
                              value={item.name}
                            />
                          </div>
                          <div
                            className="input-group mb-3"
                            style={{ display: "flex" }}
                          >
                            <span
                              className="input-group-text"
                              id="inputGroup-sizing-default"
                            >
                              GENDER
                            </span>
                            <select
                              className="form-select "
                              name="gender"
                              aria-label="Default select example"
                              style={{ width: "fit-content" }}
                              onChange={handleInputChange}
                              value={item.gender}
                            >
                              <option selected>Open this select menu</option>
                              <option value={"WOMEN"}>WOMEN</option>
                              <option value={"MEN"}>MEN</option>
                              <option value={"KIDS"}>KIDS</option>
                              <option value={"BABY"}>BABY</option>
                            </select>
                          </div>
                        </>
                      </Modal.Body>
                      <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                          Close
                        </Button>
                        <Button variant="primary" onClick={handleSaveChanges}>
                          Save Changes
                        </Button>
                      </Modal.Footer>
                    </Modal>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <nav aria-label="Page navigation example">
            <ul className="pagination justify-content-end">
              <li
                className={`page-item ${
                  !listCategory?.hasPrevious && "disabled"
                }`}
              >
                <Link
                  className="page-link"
                  onClick={() =>
                    handlePageChange(listCategory.currentPageNumber - 1)
                  }
                >
                  Previous
                </Link>
              </li>
              {Array.from({ length: listCategory?.totalPages }, (_, i) => (
                <li
                  key={i}
                  className={`page-item ${
                    listCategory.currentPageNumber === i && "active"
                  }`}
                >
                  <Link
                    className="page-link "
                    onClick={() => handlePageChange(i)}
                  >
                    {i + 1}
                  </Link>
                </li>
              ))}
              <li
                className={`page-item ${!listCategory?.hasNext && "disabled"}`}
              >
                <Link
                  className="page-link"
                  onClick={() =>
                    handlePageChange(listCategory.currentPageNumber + 1)
                  }
                >
                  Next
                </Link>
              </li>
            </ul>
          </nav>
          <ToastContainer />
        </div>
      </div>
    </div>
  );
}

export default AdminCategory;
