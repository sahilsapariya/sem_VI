import React, { useContext, useMemo } from "react";
import useFetch from "../../hooks/useFetch";
import "../styles/styles.css";
import ThemeContext from "../../context/ThemeContext";

const Home = () => {
  const {
    data: todos,
    loading,
    error,
  } = useFetch("https://jsonplaceholder.typicode.com/todos");

  const { theme, toggleTheme } = useContext(ThemeContext);

  const memoizedTodos = useMemo(() => todos, [todos]);

  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <>
      {!loading ? (
        <>
          <h1>Home page - Todos</h1>

          <div
            className="subheader"
            style={{
              backgroundColor: theme === "light" ? "#f0f0f0" : "#333",
              color: theme === "light" ? "#333" : "#f0f0f0",
            }}
          >
            <h3>Todos</h3>
            <button onClick={toggleTheme}>change theme</button>
          </div>
          <hr />

          {memoizedTodos?.map((todo, index) => {
            return <Todo todo={todo} index={index} />;
          })}
        </>
      ) : (
        <div>Loading...</div>
      )}
    </>
  );
};

const Todo = ({ todo, index }) => {
  const { theme } = useContext(ThemeContext);

  return (
    <div
      className="todo_item"
      key={index}
      style={{
        backgroundColor: theme === "light" ? "#fff" : "#222",
        color: theme === "light" ? "#333" : "#f0f0f0",
      }}
    >
      <p>Title: {todo.title}</p>
      <p>Status: {todo.completed.toString()}</p>
    </div>
  );
};

export default Home;
