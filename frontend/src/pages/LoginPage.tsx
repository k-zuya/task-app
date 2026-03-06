import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

function LoginPage() {
  const [name, setName] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const navigate = useNavigate()

  const handleLogin = async () => {
    const res = await fetch('/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name, password }),
    })

    if (res.ok) {
      const token = await res.text()
      localStorage.setItem('token', token)
      navigate('/')
    } else {
      setError('ログインに失敗しました')
    }
  }

  return (
    <div>
      <h1>ログイン</h1>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <input
        placeholder="ユーザー名"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <br />
      <input
        placeholder="パスワード"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <br />
      <button onClick={handleLogin}>ログイン</button>
    </div>
  )
}

export default LoginPage
