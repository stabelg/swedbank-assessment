insert into application_user_detail(id, name, billing_address, shipping_address)
values(0, 'Admin', 'Some billing Address', 'Some shipping address');

insert into application_user(id, username, email, password, details_id)
values(0, 'admin', 'admin@admin.com', '$2a$10$hj8NJW94lHgm5rbq.waR3.o0P3ZIw8NffhFtSxl7mg2.OhKYnQqGm', 0);
