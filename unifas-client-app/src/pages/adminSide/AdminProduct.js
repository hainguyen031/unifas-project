import React from 'react'
import Sidebar from '../../components/admin/sidebar/Sidebar'
import NewProduct from '../../components/main/product/NewProduct'

function AdminProduct() {
  return (
    <div className="list">
    <Sidebar/>
    <div className="listContainer">
      <NewProduct/>
    </div>
  </div>
  )
}

export default AdminProduct