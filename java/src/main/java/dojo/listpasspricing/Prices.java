package dojo.listpasspricing;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.LoggerFactory;

public class Prices {

    public static void createApp() throws SQLException, ClassNotFoundException {

        final Connection connection;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties connectionOptions = new Properties();
        connectionOptions.put("user", "root");
        connectionOptions.put("password", "mysql");
        // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lift_pass", connectionOptions);

        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        connection = DriverManager.getConnection("jdbc:hsqldb:file:../database/initDatabase.sql", "SA", "");

        port(4567);

        put("/prices", (req, res) -> {
            // req.queryParams("cost");
            //            const liftPassCost = req.query.cost
            //            const liftPassType = req.query.type
            //            const [rows, fields] = await connection.execute(
            //                'INSERT INTO `base_price` (type, cost) VALUES (?, ?) ' +
            //                'ON DUPLICATE KEY UPDATE cost = ?',
            //                [liftPassType, liftPassCost, liftPassCost]);
            return "";
        });

        get("/prices", (req, res) -> {
            //            const result = (await connection.query(
            //                    'SELECT cost FROM `base_price` ' +
            //                    'WHERE `type` = ? ',
            //                    [req.query.type]))[0][0]
            //
            //            let reduction;
            //            let isHoliday;
            //            if (req.query.age < 6) {
            //                res.send({cost: 0})
            //            } else {
            //                reduction = 0;
            //                if (req.query.type !== 'night') {
            //                    const holidays = (await connection.query(
            //                        'SELECT * FROM `holidays`'
            //                    ))[0]
            //    
            //                    for (let row of holidays) {
            //                        const holidayDate = row.holiday.toISOString().split('T')[0]
            //                        if (req.query.date && req.query.date === holidayDate) {
            //                            isHoliday = true
            //                        }
            //    
            //                    }
            //                    if (!isHoliday && new Date(req.query.date).getDay() === 0) {
            //                        reduction = 60
            //                    }
            //    
            //                    // TODO apply reduction for others
            //                    if (req.query.age < 15) {
            //                        res.send({cost: Math.ceil(result.cost * .7)})
            //                    } else {
            //                        if (req.query.age > 74) {
            //                            res.send({cost: Math.ceil(result.cost * .4)})
            //                        } else {
            //                            if (req.query.age === undefined) {
            //                                let cost = result.cost
            //                                if (reduction) {
            //                                    cost = cost / (1 + reduction / 100)
            //                                }
            //                                res.send({cost: Math.ceil(cost)})
            //                            } else {
            //                                if (req.query.age > 64) {
            //                                    let cost = result.cost * .75
            //                                    if (reduction) {
            //                                        cost = cost / (1 + reduction / 100)
            //                                    }
            //                                    res.send({cost: Math.ceil(cost)})
            //                                } else {
            //                                    let cost = result.cost
            //                                    if (reduction) {
            //                                        cost = cost / (1 + reduction / 100)
            //                                    }
            //                                    res.send({cost: Math.ceil(cost)})
            //                                }
            //                            }
            //                        }
            //                    }
            //                } else {
            //                    if (req.query.age >= 6) {
            //                        if (req.query.age > 74) {
            //                            res.send({cost: Math.ceil(result.cost / 2.5)})
            //                        } else {
            //                            res.send(result)
            //                        }
            //                    } else {
            //                        res.send({cost: 0})
            //                    }
            //                }

            return "TEST";
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                connection.close();
            } catch (SQLException e) {
                LoggerFactory.getLogger(Prices.class).error("connection close", e);
            }
        }));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        createApp();
    }
}
