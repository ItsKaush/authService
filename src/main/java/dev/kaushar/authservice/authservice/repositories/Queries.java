package dev.kaushar.authservice.authservice.repositories;

public interface Queries {
    String findRolesOfUser = "select role from role where role_id in (" +
            "select roles_id from user_roles where user user_id in=:userId)";

    String UpdateSessionById = "Update session set status=:sessionStatus where id=:Id";
}
