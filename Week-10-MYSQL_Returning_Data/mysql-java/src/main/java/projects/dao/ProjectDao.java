package projects.dao;

import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
import provided.util.DaoBase;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import projects.exception.DbException;

public class ProjectDao extends DaoBase{
	  private static final String CATEGORY_TABLE = "category";
	  private static final String MATERIAL_TABLE = "material";
	  private static final String PROJECT_TABLE = "project";
	  private static final String PROJECT_CATEGORY_TABLE = "project_category";
	  private static final String STEP_TABLE = "step";

	public Project insertProject(Project project) {

		// @formatter:off
		String sql = ""
				+ "INSERT INTO " + PROJECT_TABLE + " "
				+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
				+ "VALUES "
				+ "(?,?,?,?,?)"
				;
		// @formatter:on
		try (Connection con = DbConnection.getConnection()){
			startTransaction(con);
			
			try (PreparedStatement stmt = con.prepareStatement(sql)){
				setParameter(stmt, 1, project.getProjectName(), String.class);
		        setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
		        setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
		        setParameter(stmt, 4, project.getDifficulty(), Integer.class);
		        setParameter(stmt, 5, project.getNotes(), String.class);

		        stmt.executeUpdate();

		        Integer projectId = getLastInsertId(con, PROJECT_TABLE);
		        commitTransaction(con);

		        project.setProjectId(projectId);
		        return project;
			}catch(Exception e){
				rollbackTransaction(con);
				throw new DbException(e);
			}
				
		}catch(SQLException e) {
			throw new DbException(e);
		}
	}

	public List<Project> fetchAllProjects() {
		// @formatter:off
		String sql = ""
				+ "SELECT *"
				+ "FROM " + PROJECT_TABLE + " "
				+ "ORDER BY project_name" 
				;
		// @formatter:on
		try(Connection con = DbConnection.getConnection()){
			startTransaction(con);
			
			try(PreparedStatement stmt = con.prepareStatement(sql)){
				
				try(ResultSet result = stmt.executeQuery()){
					List<Project> projects = new LinkedList<>();
					while(result.next()) {
						projects.add(extract(result, Project.class));
					}
					return projects;
				}catch(Exception e) {
					throw new DbException(e);
				}
			}catch(Exception e) {
				rollbackTransaction(con);
				throw new DbException(e);
			}
			
		}catch(SQLException e) {
			throw new DbException(e);
		}
	}

	public Optional<Project> fetchProjectbyId(int projectId) {
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM " + PROJECT_TABLE + " "
				+ "WHERE project_id=?"
				;
		// @formatter:on
		
		try(Connection con = DbConnection.getConnection()){
			startTransaction(con);
			try{
				Project project = null;
				
				try(PreparedStatement stmt = con.prepareStatement(sql)){
					setParameter(stmt, 1, projectId, Integer.class);
					
					try(ResultSet result = stmt.executeQuery()){
						while(result.next()) {
							project = extract(result, Project.class);
						}
					}
				}
				
				if(Objects.nonNull(project)) {
					project.getMaterials().addAll(fetchMaterialsForProject(con, projectId));
					project.getSteps().addAll(fetchStepsForProject(con, projectId));
					project.getCategories().addAll(fetchCategoriesForProject(con,projectId));
				}
				
				commitTransaction(con);
				return Optional.ofNullable(project);
				
			}catch(Exception e) {
				rollbackTransaction(con);
				throw new DbException(e);
			}
			
		}catch(SQLException e) {
			throw new DbException(e);
		}
		
	}

	private Collection<? extends Category> fetchCategoriesForProject(Connection con, int projectId) throws SQLException {
		// @formatter:off
		String sql = ""
				+ "SELECT c.* "
				+ "FROM " + CATEGORY_TABLE + " c "
				+ "JOIN " + PROJECT_CATEGORY_TABLE + " pc USING (category_id) "
				+ "WHERE pc.project_id = ?"
				;
		// @formatter:on
		
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			setParameter(stmt, 1, projectId, Integer.class);
			try(ResultSet result = stmt.executeQuery()){
				List<Category> categories = new LinkedList<>();
				
				while(result.next()) {
					categories.add(extract(result, Category.class));
				}
				return categories;
			}
	
		}
	}

	private Collection<? extends Step> fetchStepsForProject(Connection con, int projectId) throws SQLException {
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM " + STEP_TABLE + " "
				+ "WHERE project_id = ?"
				;
		// @formatter:on
		
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			setParameter(stmt, 1, projectId, Integer.class);
			try(ResultSet result = stmt.executeQuery()){
				List<Step> steps = new LinkedList<>();
				
				while(result.next()) {
					steps.add(extract(result, Step.class));
				}
				return steps;
			}
	
		}
	}

	private Collection<? extends Material> fetchMaterialsForProject(Connection con, int projectId) throws SQLException {
		// @formatter:off
		String sql = ""
				+ "SELECT * "
				+ "FROM " + MATERIAL_TABLE + " "
				+ "WHERE project_id = ?"
				;
		// @formatter:on
		
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			setParameter(stmt, 1, projectId, Integer.class);
			try(ResultSet result = stmt.executeQuery()){
				List<Material> materials = new LinkedList<>();
				
				while(result.next()) {
					materials.add(extract(result, Material.class));
				}
				return materials;
			}
	
		}
	}

}
