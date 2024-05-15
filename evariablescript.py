import os
def read_env_file(filename):
    env_path = os.path.join(filename, 'evariable.yaml')
    with open(env_path, 'r') as file:
        data = file.read()
    return data


def generate_app_yaml(template_file, env_vars, output_file):
    template_path = os.path.join(template_file, 'app_template.yaml')
    with open(template_path,'r') as file:
        template_content = file.read()
    final_content = f"{template_content}\n{env_vars}"

    output_path = os.path.join(output_file, 'app.yaml')
    with open(output_path, 'w') as file:
        file.write(final_content)
        print("app.yaml has been successfully completed.")

if __name__=="__main__":
    project_dir = r'C:\Users\brend\Documents\sakilademo'
    env_vars = read_env_file(project_dir)
    output_dir = os.path.join(project_dir, 'src', 'main', 'appengine')
    generate_app_yaml(project_dir, env_vars, output_dir)