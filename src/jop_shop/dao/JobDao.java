package jop_shop.dao;

import jop_shop.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobDao extends BaseDao {
	public boolean add(Assembly assembly, Job job) {
		System.out.println("Add Assembly: " + assembly.toString());
		System.out.println("Add Job: " + job.toString());
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder("INSERT INTO assembly(date_ordered, assembly_detail, customer_id) VALUES (?,?,?)");
			ps = connection.prepareStatement(sql.toString());
			ps.setDate(1, assembly.getDateOrdered());
			ps.setString(2, assembly.getAssemblyDetail());
			ps.setInt(3, assembly.getCustomerId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int assemblyId = rs.getInt(1);
				sql = new StringBuilder("INSERT INTO job(completed_date, commenced_date, assembly_id, process_id, labor_time) VALUES (?,?,?,?,?)");
				ps = connection.prepareStatement(sql.toString());
				ps.setDate(1, job.getCompletedDate());
				ps.setDate(2, job.getCommencedDate());
				ps.setInt(3, assemblyId);
				ps.setInt(4, job.getProcessId());
				ps.setInt(5, job.getLaborTime());
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int jobNumber = rs.getInt(1);
					if (job instanceof FitJob) {
						sql = new StringBuilder("INSERT INTO fit_job(job_number) VALUES (?)");
						ps = connection.prepareStatement(sql.toString());
						ps.setInt(1, jobNumber);
						ps.executeUpdate();
					} else if (job instanceof CutJob) {
						CutJob cutJob = (CutJob) job;
						sql = new StringBuilder("INSERT INTO cut_job(job_number, machine_type, machine_used_time, material_used) VALUES (?,?,?,?)");
						ps = connection.prepareStatement(sql.toString());
						ps.setInt(1, jobNumber);
						ps.setString(2, cutJob.getMachineType());
						ps.setInt(3, cutJob.getMachineUsedTime());
						ps.setString(4, cutJob.getMaterialUsed());
						ps.executeUpdate();
					} else {
						PaintJob paintJob = (PaintJob) job;
						sql = new StringBuilder("INSERT INTO paint_job(job_number, color, volume) VALUES (?,?,?)");
						ps = connection.prepareStatement(sql.toString());
						ps.setInt(1, jobNumber);
						ps.setString(2, paintJob.getColor());
						ps.setInt(3, paintJob.getVolume());
						ps.executeUpdate();
					}
					connection.commit();
					return true;
				}
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {

			}
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return false;
	}

	public boolean add(Job job) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder("INSERT INTO job(completed_date, commenced_date, assembly_id, process_id, labor_time) VALUES (?,?,?,?,?)");
			ps = connection.prepareStatement(sql.toString());

			ps.setDate(1, job.getCompletedDate());
			ps.setDate(2, job.getCommencedDate());
			ps.setInt(3, job.getAssemblyId());
			ps.setInt(4, job.getProcessId());
			ps.setInt(5, job.getLaborTime());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int jobNumber = rs.getInt(1);
				if (job instanceof FitJob) {
					sql = new StringBuilder("INSERT INTO fit_job(job_number) VALUES (?)");
					ps = connection.prepareStatement(sql.toString());
					ps.setInt(1, jobNumber);
					ps.executeUpdate();
				} else if (job instanceof CutJob) {
					CutJob cutJob = (CutJob) job;
					sql = new StringBuilder("INSERT INTO cut_job(job_number, machine_type, machine_used_time, material_used) VALUES (?,?,?,?)");
					ps = connection.prepareStatement(sql.toString());
					ps.setInt(1, jobNumber);
					ps.setString(2, cutJob.getMachineType());
					ps.setInt(3, cutJob.getMachineUsedTime());
					ps.setString(4, cutJob.getMaterialUsed());
					ps.executeUpdate();
				} else {
					PaintJob paintJob = (PaintJob) job;
					sql = new StringBuilder("INSERT INTO paint_job(job_number, color, volume) VALUES (?,?,?)");
					ps = connection.prepareStatement(sql.toString());
					ps.setInt(1, jobNumber);
					ps.setString(2, paintJob.getColor());
					ps.setInt(3, paintJob.getVolume());
					ps.executeUpdate();
				}
				connection.commit();
				return true;
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {

			}
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return false;
	}
}
