import {useState} from "react";
import {useEntities} from "../../util/useEntities";
import {WorkingHours} from "../../api/base";
import {Property} from "../Property";
import {staffApi} from "../../api/StaffApi";


interface Props {
    workingHours?: WorkingHours
    onSubmit: (workingHours: WorkingHours) => void
}

export const WorkingHoursForm: React.FC<Props> = ({ workingHours, onSubmit }) => {

    const [staffs] = useEntities(staffApi)

    const [datestart, setDateStart] = useState(workingHours?.datestart ?? '')
    const [dateend, setDateEnd] = useState(workingHours?.dateend ?? '')


    const [selectStaff, setSelectStaff] = useState<string[]>(staffs?.map(o => o.id?.toString() ?? '') ?? [])


    const onClick = () => {
        if (datestart === '') return
        onSubmit({
            datestart,
            dateend,

            staffByStaff: staffs?.find(st => !!selectStaff.find(ts => ts === st.id?.toString() ?? '-1'))
        })
        setDateStart('')
        setDateEnd('')

        setSelectStaff([])
    }

    return (
        <div className="visitor-form">
            <Property title="Дата начала работы:" value={<input type="text" value={datestart} onChange={e => setDateStart(e.target.value)} />} />
            <Property title="Дата окончания работы:" value={<input type="text" value={dateend} onChange={e => setDateEnd(e.target.value)} />} />


            <Property title="Информация о сотрудниках">
                <select multiple value={selectStaff}
                        onChange={e => setSelectStaff(Array.from(e.target.selectedOptions, option => option.value))}>
                    {staffs?.map(m => <option key={m.id}
                                                 value={m.id}>{m.name}</option>)}
                </select>
            </Property>
            <button className="button button_green" onClick={onClick}>Ок</button>
        </div>
    )
}