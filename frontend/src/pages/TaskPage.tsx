import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

function TaskPage() {
    const [title, setTitle] = useState('')
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

    const handleCreate = async () => {
        if (!title) return

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

    const handleEdit = async (id: number) => {
        const res = await fetch(`/api/tasks/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
            body: JSON.stringify({ title: editTitle }),
        })
        if (res.ok) {
            setEditingId(null)
            fetchTasks()
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

    const getStatusClass = (status: string) => {
        switch (status) {
            case 'TODO': return 'status-todo'
            case 'IN_PROGRESS': return 'status-in-progress'
            case 'DONE': return 'status-done'
            default: return 'status-todo'
        }
    }

    const getStatusLabel = (status: string) => {
        switch (status) {
            case 'TODO': return 'TODO'
            case 'IN_PROGRESS': return '進行中'
            case 'DONE': return '完了'
            default: return status
        }
    }

    useEffect(() => {
        fetchTasks()
    }, [])

    return (
        <div className="task-wrapper">
            <nav className="task-navbar">
                <div className="navbar-brand">
                    <div className="navbar-brand-icon">T</div>
                    TaskApp
                </div>
                <button className="logout-button" onClick={handleLogout}>ログアウト</button>
            </nav>

            <div className="task-content">
                <div className="task-form">
                    <input
                        placeholder="新しいタスクを追加..."
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        onKeyDown={(e) => e.key === 'Enter' && handleCreate()}
                    />
                    <button className="create-button" onClick={handleCreate}>追加</button>
                </div>

                <div className="task-list-header">
                    <h2>タスク</h2>
                    <span className="task-count">{tasks.length}件</span>
                </div>

                {tasks.length === 0 ? (
                    <div className="empty-state">
                        <div className="empty-state-icon">📋</div>
                        <p>タスクがありません。上のフォームから追加しましょう！</p>
                    </div>
                ) : (
                    <ul className="task-list">
                        {tasks.map((task: any) => (
                            <li key={task.id} className={`task-item ${editingId === task.id ? 'task-item-editing' : ''}`}>
                                {editingId === task.id ? (
                                    <>
                                        <input
                                            className="edit-input"
                                            value={editTitle}
                                            onChange={(e) => setEditTitle(e.target.value)}
                                            onKeyDown={(e) => e.key === 'Enter' && handleEdit(task.id)}
                                        />
                                        <div className="task-actions">
                                            <button className="save-button" onClick={() => handleEdit(task.id)}>保存</button>
                                            <button className="cancel-button" onClick={() => setEditingId(null)}>取消</button>
                                        </div>
                                    </>
                                ) : (
                                    <>
                                        <span className="task-info">
                                            <span className="task-title">{task.title}</span>
                                            <span className={`task-status ${getStatusClass(task.status)}`}>
                                                {getStatusLabel(task.status)}
                                            </span>
                                        </span>
                                        <div className="task-actions">
                                            <button className="edit-button" onClick={() => { setEditingId(task.id); setEditTitle(task.title) }}>編集</button>
                                            <button className="delete-button" onClick={() => handleDelete(task.id)}>削除</button>
                                        </div>
                                    </>
                                )}
                            </li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    )
}

export default TaskPage
