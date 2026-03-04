import { useState, useEffect } from 'react'

function App() {
  const [users, setUsers] = useState([])

useEffect(() => {
  fetch("/api/users")
    .then(res => res.json())
    .then(data => setUsers(data))
}, [])
  return (
    <>
      <h1>タスク共有アプリ</h1>
      <h2>ユーザー一覧</h2>
      <ul>
  {users.map((user: any) => (
    <li key={user.id}>{user.name}（{user.role}）</li>
  ))}
</ul>

    </>
  )
}

export default App