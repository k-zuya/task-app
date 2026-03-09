import { useState, useEffect } from 'react'

function TaskPage() {
    const [title, setTitle] = useState('')  // ← useState を使う

    const [tasks, setTasks] = useState([])



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
    useEffect(() => {
        fetchTasks()
    }, [])

    return (
        <div>
            <h1>タスク一覧</h1>
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
                        <li key={task.id}>{task.title}（{task.status}）</li>
                    ))}
                </ul>

            </div>
        </div>
    )
}

export default TaskPage
