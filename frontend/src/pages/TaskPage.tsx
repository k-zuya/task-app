import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

function TaskPage() {
    const [title, setTitle] = useState('')  // ← useState を使う
    const navigate = useNavigate()
    const [tasks, setTasks] = useState([])



        const handleLogout = () => {
            localStorage.removeItem('token')
            navigate('/login')
        }

    const fetchTasks = () => {
        fetch('/api/tasks', {
            headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') },
        })
            .then(res => res.json())
            .then(data => setTasks(data))
    }
    const handleCreate = async () => {     // ← 作成関数
        const res = await fetch('/api/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
            body: JSON.stringify({ title }),
        })
    }
    const handleDelete = async (id: number) => {
        const res = await fetch(`/api/tasks/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
        })

        if (res.ok) {
            fetchTasks()
        }
    }
    useEffect(() => {
        fetchTasks()
    }, [])

    return (
        <div>
            <h1>タスク一覧</h1>
            <button onClick={handleLogout}>ログアウト</button>
            <div>
                <input
                    placeholder="タスク"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <br />
                <button onClick={handleCreate}>作成</button>
                <ul>
                    {tasks.map((task: any) => (
                        <li key={task.id}>{task.title}（{task.status}）<button onClick={() => handleDelete(task.id)}>削除</button></li>

                    ))}
                </ul>

            </div>
        </div>
    )
}

export default TaskPage
