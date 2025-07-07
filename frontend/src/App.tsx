import React, { useState, useEffect } from 'react';
import './App.css';

interface ApiResponse {
  message: string;
  timestamp: string;
  status: string;
}

function App() {
  const [counter, setCounter] = useState<number | null>(null);
  const [counterLoading, setCounterLoading] = useState(false);
  const [counterError, setCounterError] = useState<string | null>(null);
  const [apiResponse, setApiResponse] = useState<ApiResponse | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  // Fetch counter value on mount
  useEffect(() => {
    fetchCounter();
  }, []);

  const fetchCounter = async () => {
    setCounterLoading(true);
    setCounterError(null);
    try {
      const response = await fetch('/api/counter');
      if (!response.ok) throw new Error('Failed to fetch counter');
      const value = await response.text();
      setCounter(Number(value));
    } catch (err) {
      setCounterError(err instanceof Error ? err.message : 'An error occurred');
    } finally {
      setCounterLoading(false);
    }
  };

  const increment = async () => {
    setCounterLoading(true);
    setCounterError(null);
    try {
      const response = await fetch('/api/counter/increment', { method: 'POST' });
      if (!response.ok) throw new Error('Failed to increment');
      const value = await response.text();
      setCounter(Number(value));
    } catch (err) {
      setCounterError(err instanceof Error ? err.message : 'An error occurred');
    } finally {
      setCounterLoading(false);
    }
  };

  const decrement = async () => {
    setCounterLoading(true);
    setCounterError(null);
    try {
      const response = await fetch('/api/counter/decrement', { method: 'POST' });
      if (!response.ok) throw new Error('Failed to decrement');
      const value = await response.text();
      setCounter(Number(value));
    } catch (err) {
      setCounterError(err instanceof Error ? err.message : 'An error occurred');
    } finally {
      setCounterLoading(false);
    }
  };

  const reset = fetchCounter;

  const testApi = async () => {
    setLoading(true);
    setError(null);
    setApiResponse(null);
    
    try {
      const response = await fetch('/api/hello');
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      setApiResponse(data);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An error occurred');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="App">
      <div className="container">
        <header className="header">
          <h1>🚀 Spring Boot + React Demo</h1>
          <p>Modern full-stack application with Spring Boot backend and React frontend</p>
        </header>

        <div className="counter-section">
          <h2>Counter Component</h2>
          <div className="counter-display">
            <span className="counter-value">
              {counterLoading ? <span className="loading" /> : counter}
            </span>
          </div>
          <div className="counter-controls">
            <button onClick={increment} className="btn btn-primary" disabled={counterLoading}>
              ➕ Increment
            </button>
            <button onClick={decrement} className="btn btn-secondary" disabled={counterLoading}>
              ➖ Decrement
            </button>
            <button onClick={reset} className="btn btn-reset" disabled={counterLoading}>
              🔄 Reset
            </button>
          </div>
          {counterError && (
            <div className="api-response error">
              <div className="status-badge error">❌ Error</div>
              <p>{counterError}</p>
            </div>
          )}
        </div>

        <div className="api-section">
          <h2>API Integration</h2>
          <p>Test the Spring Boot backend API:</p>
          <button 
            onClick={testApi} 
            className="btn btn-api"
            disabled={loading}
          >
            {loading ? '⏳ Loading...' : '🔗 Test API'}
          </button>
          
          {error && (
            <div className="api-response error">
              <div className="status-badge error">❌ Error</div>
              <p>{error}</p>
              <p className="note">Note: Make sure the Spring Boot backend is running on port 8080</p>
            </div>
          )}
          
          {apiResponse && (
            <div className="api-response success">
              <div className="status-badge success">✅ Success</div>
              <pre>{JSON.stringify(apiResponse, null, 2)}</pre>
            </div>
          )}
        </div>

        <div className="features-section">
          <h2>Features</h2>
          <div className="features-grid">
            <div className="feature-card">
              <h3>⚛️ React 19</h3>
              <p>Latest React with TypeScript support</p>
            </div>
            <div className="feature-card">
              <h3>🍃 Spring Boot</h3>
              <p>RESTful API backend with Java</p>
            </div>
            <div className="feature-card">
              <h3>🎨 Modern UI</h3>
              <p>Beautiful gradients and animations</p>
            </div>
            <div className="feature-card">
              <h3>🔗 API Integration</h3>
              <p>Seamless frontend-backend communication</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
