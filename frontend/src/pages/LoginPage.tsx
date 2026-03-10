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
      setError('ユーザー名またはパスワードが正しくありません')
    }
  }

  return (
    <div className="login-wrapper">
      <div className="login-container">
        <h1>TaskApp</h1>
        <p className="login-subtitle">アカウントにログイン</p>
        {error && <p className="error-message">{error}</p>}
        <div className="form-group">
          <label>ユーザー名</label>
          <input
            placeholder="ユーザー名を入力"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label>パスワード</label>
          <input
            placeholder="パスワードを入力"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button className="login-button" onClick={handleLogin}>ログイン</button>
      </div>
    </div>
  )
}

export default LoginPage
