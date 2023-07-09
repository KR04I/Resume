import React from 'react'
import { Navigate, Route, Routes } from 'react-router'
import { BrowserRouter } from 'react-router-dom'
import './App.css'
import {PassportPage} from "./component/passport/PassportPage";
import { Header } from './component/Header'
import {RegistrationPage} from "./component/registration/RegistrationPage";
import {RoomPage} from "./component/room/RoomPage";
import {ServicePage} from "./component/service/ServicePage";
import {StaffPage} from "./component/staff/StaffPage";
import {VisitorPage} from "./component/visitor/VisitorPage";
import {WorkingHoursPage} from "./component/workinghours/WorkingHoursPage";
export const App: React.FC = () => {
  return (
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/passport" element={<PassportPage/>} />
          <Route path="/registration" element={<RegistrationPage/>} />
          <Route path="/room" element={<RoomPage/>} />
          <Route path="/services" element={<ServicePage/>} />
          <Route path="/staff" element={<StaffPage/>} />
          <Route path="/visitor" element={<VisitorPage/>} />
          <Route path="/workinghours" element={<WorkingHoursPage/>} />
        </Routes>
      </BrowserRouter>
  )
}