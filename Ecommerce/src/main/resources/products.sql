-- === Products ===
INSERT INTO product (sku, name, price, status, version) VALUES ('TEE-001', 'Basic Tee', 19.99, 'ACTIVE', 0);
INSERT INTO product (sku, name, price, status, version) VALUES ('MUG-001', 'Ceramic Mug', 12.50, 'ACTIVE', 0);
INSERT INTO product (sku, name, price, status, version) VALUES ('HAT-001', 'Baseball Cap', 24.00, 'ACTIVE', 0);
INSERT INTO product (sku, name, price, status, version) VALUES ('SWT-001', 'Classic Hoodie', 49.00, 'ACTIVE', 0);
INSERT INTO product (sku, name, price, status, version) VALUES ('SCK-001', 'Ankle Socks (2-Pack)', 9.50, 'ACTIVE', 0);
INSERT INTO product (sku, name, price, status, version) VALUES ('STK-001', 'Sticker Pack', 3.99, 'INACTIVE', 0);

-- === Inventory ===
INSERT INTO inventory (product_id, on_hand, reserved, version)
VALUES ((SELECT id FROM product WHERE sku='TEE-001'), 100, 0, 0);

INSERT INTO inventory (product_id, on_hand, reserved, version)
VALUES ((SELECT id FROM product WHERE sku='MUG-001'), 80, 0, 0);

INSERT INTO inventory (product_id, on_hand, reserved, version)
VALUES ((SELECT id FROM product WHERE sku='HAT-001'), 60, 0, 0);

INSERT INTO inventory (product_id, on_hand, reserved, version)
VALUES ((SELECT id FROM product WHERE sku='SWT-001'), 40, 0, 0);

INSERT INTO inventory (product_id, on_hand, reserved, version)
VALUES ((SELECT id FROM product WHERE sku='SCK-001'), 150, 0, 0);

INSERT INTO inventory (product_id, on_hand, reserved, version)
VALUES ((SELECT id FROM product WHERE sku='STK-001'), 0, 0, 0); -- inactive product, zero stock

-- Verify
SELECT p.id, p.sku, p.name, p.price, p.status, i.on_hand, i.reserved
FROM product p LEFT JOIN inventory i ON i.product_id = p.id
ORDER BY p.id;