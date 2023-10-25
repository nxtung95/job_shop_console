package jop_shop.dao;

import jop_shop.model.CutProcess;
import jop_shop.model.FitProcess;
import jop_shop.model.PaintProcess;
import jop_shop.model.Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<Process> findAll() {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Process> processList = new ArrayList<>();
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT a.process_id, a.process_data, a.department_id, b.fit_type as extra1, '' as extra2, '0' as type FROM process a JOIN fit_process b ON a.process_id = b.process_id ");
			sql.append("UNION ALL SELECT a.process_id, a.process_data, a.department_id, b.cutting_type as extra1, b.machine_type as extra2, '1' as type FROM process a JOIN cut_process b ON a.process_id = b.process_id ");
			sql.append("UNION ALL SELECT a.process_id, a.process_data, a.department_id, b.paint_type as extra1, b.paint_method as extra2, '2' as type FROM process a JOIN paint_process b ON a.process_id = b.process_id ");
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int processId = rs.getInt("process_id");
				String processData = rs.getString("process_data");
				int departmentId = rs.getInt("department_id");
				String extra1 = rs.getString("extra1");
				String extra2 = rs.getString("extra2");
				String type = rs.getString("type");
				Process process;
				if ("0".equals(type)) {
					process = new FitProcess(processId, processData, departmentId, extra1);
				} else if ("1".equals(type)) {
					process = new CutProcess(processId, processData, departmentId, extra1, extra2);
				} else {
					process = new PaintProcess(processId, processData, departmentId, extra1, extra2);
				}
				processList.add(process);
			}
			return processList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return processList;
	}

	public List<Process> findAllByIds(List<Integer> processIds) {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Process> processList = new ArrayList<>();
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT a.process_id, a.process_data, a.department_id, b.fit_type as extra1, '' as extra2, '0' as type FROM process a " +
					"JOIN fit_process b ON a.process_id = b.process_id WHERE a.process_id IN ? ");
			sql.append("UNION ALL SELECT a.process_id, a.process_data, a.department_id, b.cutting_type as extra1, b.machine_type as extra2, '1' as type FROM process a " +
					"JOIN cut_process b ON a.process_id = b.process_id WHERE a.process_id IN ? ");
			sql.append("UNION ALL SELECT a.process_id, a.process_data, a.department_id, b.paint_type as extra1, b.paint_method as extra2, '2' as type FROM process a " +
					"JOIN paint_process b ON a.process_id = b.process_id WHERE a.process_id IN ? ");

			String processIdParam = "(" + String.join(",", processIds.stream().map(i -> String.valueOf(i)).collect(Collectors.toList())) + ")";
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, processIdParam);
			ps.setString(2, processIdParam);
			ps.setString(3, processIdParam);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int processId = rs.getInt("process_id");
				String processData = rs.getString("process_data");
				int departmentId = rs.getInt("department_id");
				String extra1 = rs.getString("extra1");
				String extra2 = rs.getString("extra2");
				String type = rs.getString("type");
				Process process;
				if ("0".equals(type)) {
					process = new FitProcess(processId, processData, departmentId, extra1);
				} else if ("1".equals(type)) {
					process = new CutProcess(processId, processData, departmentId, extra1, extra2);
				} else {
					process = new PaintProcess(processId, processData, departmentId, extra1, extra2);
				}
				processList.add(process);
			}
			return processList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return processList;
	}

	public List<Process> finAllByAssemblyAndJobCompleted(int assemblyId) {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Process> processList = new ArrayList<>();
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT a.process_id, b.department_id, b.department_data FROM process a ");
			sql.append("JOIN job b ON a.process_id = b.process_id ");
			sql.append("JOIN assembly c ON c.assembly_id = b.assembly_id ");
			sql.append("JOIN department d ON d.department_id = c.department_id ");
			sql.append("WHERE c.assembly_id = ? AND b.completed_date IS NOT NULL");
			sql.append("GROUP BY a.process_id, b.department_id, b.department_data ");
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, assemblyId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int processId = rs.getInt("process_id");
				int departmentId = rs.getInt("department_id");
				String departmentData = rs.getString("department_data");

			}
			return processList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return processList;
	}
}
