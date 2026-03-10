import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

function TaskPage() {
    const [title, setTitle] = useState('')  // ← useState を使う
    const navigate = useNavigate()
    const [tasks, setTasks] = useState([])
    const [editingId, setEditingId] = useState(null)
    const [editTitle, setEditTitle] = useState('')




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
        if (res.ok) {
            setTitle('')
            fetchTasks()
        }
    }
    const handleEdit = async (id: number) => {     // ← 作成関数
        const res = await fetch(`/api/tasks/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
            body: JSON.stringify({ title: editTitle }),
        })
        if (res.ok) {
            setEditingId(null)  // 編集モード終了
            fetchTasks()        // 一覧更新
        }
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
                        <li key={task.id}>
                            {editingId === task.id ? (
                                <>
                                    <input value={editTitle} onChange={(e) => setEditTitle(e.target.value)} />
                                    <button onClick={() => handleEdit(task.id)}>保存</button>
                                </>
                            ) : (
                                <>
                                    {task.title}（{task.status}）
                                    <button onClick={() => { setEditingId(task.id); setEditTitle(task.title) }}>編集</button>
                                    <button onClick={() => handleDelete(task.id)}>削除</button>
                                </>
                            )}
                        </li>

                    ))}
                </ul>

            </div>
        </div>
    )
}

export default TaskPage
