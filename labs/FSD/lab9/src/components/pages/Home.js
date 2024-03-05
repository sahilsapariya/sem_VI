import React, { useEffect } from "react";
import "../styles/Home.css";

import { useDispatch, useSelector } from "react-redux";
import { fetchTodos } from "../features/todoSlice";

const Home = () => {
  const dispatch = useDispatch();
  const state = useSelector((state) => state.todo);

  const todos = state.data;

  if (todos) {
    var completedTodos = todos.slice(0, 20)?.filter((todo) => todo.completed);
    var pendingTodos = todos.slice(0, 20)?.filter((todo) => !todo.completed);
  }

  useEffect(() => {
    if (!todos) {
      dispatch(fetchTodos());
    }
  }, [dispatch, todos]);

  return (
    <div className="home__container">
      <TodosList header={"Pending Tasks"} todoList={pendingTodos} />
      <TodosList header={"Completed Tasks"} todoList={completedTodos} />
    </div>
  );
};

const TodosList = ({ todoList, header }) => {
  return (
    <>
      <div className="todos__container">
        <h1>{header}</h1>

        <div className="todos__content">
          {todoList?.map((todo) => {
            return <TodoCard todo={todo} key={todo.id} />;
          })}
        </div>
      </div>
    </>
  );
};

const TodoCard = ({ todo }) => {
  return (
    <div className="todos__card">
      <b>{todo.title}</b>
      <p>UserId : {todo.userId}</p>
    </div>
  );
};

export default Home;
