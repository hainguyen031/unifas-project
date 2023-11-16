
import React from 'react';
import { Link } from 'react-router-dom';

const UserProfile = ({ username, onLogout }) => {
  return (
    <div>
      <h2>Welcome, {username}!</h2>
      <nav>
        <ul>
          <li>
            <Link to="/update-profile">Update Profile</Link>
          </li>
          <li>
            <button onClick={onLogout}>Logout</button>
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default UserProfile;
