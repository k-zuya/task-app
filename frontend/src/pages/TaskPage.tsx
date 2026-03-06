import { useState, useEffect } from 'react'

function TaskPage() {
  const [tasks, setTasks] = useState([])
  const token = localStorage.getItem('token')

  useEffect(() => {
    fetch('/api/tasks', {
      headers: { 'Authorization': 'Bearer ' + token },
    })
      .then(res => res.json())
      .then(data => setTasks(data))
  }, [])

  return (
    <div>
      <h1>タスク一覧</h1>
      <ul>
        {tasks.map((task: any) => (
          <li key={task.id}>{task.title}（{task.status}）</li>
        ))}
      </ul>
    </div>
  )
}

export default TaskPage
