import React from 'react'
import { Link } from 'react-router-dom'

import './header.css'

export const Header: React.FC = () => {

    return (
        <div className="header-outer">
            <div className="header container">
                <div className="header__title">
                    <span>RGR</span>
                </div>
                <div className="header__line" />
                <div className="header__links">
                    <Link className="header__link" to="/passport">Паспорт</Link>
                    <Link className="header__link" to="/registration">Регистрационные данные</Link>
                    <Link className="header__link" to="/room">Комната</Link>
                    <Link className="header__link" to="/services">Услуги</Link>
                    <Link className="header__link" to="/staff">Персонал</Link>
                    <Link className="header__link" to="/visitor">Посетители</Link>
                    <Link className="header__link" to="/workinghours">Рабочее время</Link>
                </div>
            </div>
        </div>
    )
}