import {useEntities} from "../../util/useEntities";
import {useState} from "react";
import {Passport} from "../../api/base";
import {passportApi} from "../../api/PassportApi";
import {PassportCard} from "./PassportCard";
import {PassportForm} from "./PassportForm";

export const PassportPage: React.FC = () => {
    const [passports, _, refresh] = useEntities(passportApi)
    const [addFormShow, setAddFormShow] = useState(false)

    const onAddSubmit = (client: Passport) => {
        passportApi.create(client).finally(refresh)
        setAddFormShow(false)
    }

    const onEdit = (id?: number, client?: Passport) => {
        if (!id || !client) return
        passportApi.edit(id, client).finally(refresh)
    }

    const onDelete = (id?: number) => {
        if (!id) return
        passportApi.delete(id).finally(refresh)
    }

    return (
        <div className="passport-page">
            <div className="card">
                <button className="button" onClick={() => setAddFormShow(!addFormShow)}>{`${addFormShow ? 'Закрыть' : 'Добавить'}`}</button>
                {addFormShow &&
                    <PassportForm onSubmit={onAddSubmit} />
                }
            </div>
            <div>
                {passports?.map(c => <PassportCard key={c.id} passport={c} onEdit={onEdit} onDelete={onDelete} />)}
            </div>
        </div>
    )
}