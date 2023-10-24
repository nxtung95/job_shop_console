package jop_shop.dao;

import jop_shop.model.CutProcess;
import jop_shop.model.FitProcess;
import jop_shop.model.PaintProcess;
import jop_shop.model.Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessDao extends BaseDao {
	public boolean add(Process process) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder("INSERT INTO process (process_data, department_id) VALUES (?,?)");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, process.getProcessData());
			ps.setInt(2, process.getDepartmentId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int processId = rs.getInt(1);
				if (process instanceof FitProcess) {
					FitProcess fitProcess = (FitProcess) process;
					sql = new StringBuilder("INSERT INTO fit_process (process_id, fit_type) VALUES (?,?)");
					ps = connection.prepareStatement(sql.toString());
					ps.setInt(1, processId);
					ps.setString(2, fitProcess.getFitType());
					ps.executeUpdate();
				} else if (process instanceof CutProcess) {
					CutProcess cutProcess = (CutProcess) process;
					sql = new StringBuilder("INSERT INTO cut_process (process_id, cutting_type, machine_type) VALUES (?,?,?)");
					ps = connection.prepareStatement(sql.toString());
					ps.setInt(1, processId);
					ps.setString(2, cutProcess.getCuttingType());
					ps.setString(3, cutProcess.getMachineType());
					ps.executeUpdate();
				} else if (process instanceof PaintProcess) {
					PaintProcess paintProcess = (PaintProcess) process;
					sql = new StringBuilder("INSERT INTO paint_process (process_id, paint_type, paint_method) VALUES (?,?,?)");
					ps = connection.prepareStatement(sql.toString());
					ps.setInt(1, processId);
					ps.setString(2, paintProcess.getPaintType());
					ps.setString(3, paintProcess.getPaintMethod());
					ps.executeUpdate();
				}
				connection.commit();
				return true;
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException sqlException) {

			}
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return false;
	}
}
